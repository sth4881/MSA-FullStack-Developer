
/*
 * @(#)ConnectionPool.java	1.12 98/02/27
 *
 * Copyright (c) 02/27/98 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */

package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * Manages a collection of similar database connections.
 * <p>
 * All the attributes of <code>java.sql.Connection</code> can be set on the pool
 * and will be applied to all new <code>Connections</code> that are requested.
 * <p>
 * Applications should load JDBC driver(s) in the normal way before creating a
 * <code>ConnectionPool</code>.
 * <p>
 * The number of <code>Connection</code>s a <code>ConnectionPool</code> object
 * manages is specified when the <code>ConnectionPool</code> is created. If all
 * <code>Connections</code> are in use when a <code>Connection</code> is
 * requested the request will either terminate immediately or will block. A
 * timeout value can be specified to terminate the block.
 * <p>
 * If an app has cause to close a <code>Connection</code> because, for example,
 * it is broken, then it should still be released so that a new
 * <code>Connection</code> can be created to take its place.
 * <p>
 * The default properties for all <code>Connection</code>s in a pool are copied
 * from the first <code>Connection</code> created. The defaults may be changed
 * by calling the appropriate accessor methods. These defaults are applied to
 * all <code>Connection</code>s as they are allocated.
 * <p>
 * By default, <code>Connections</code> are re-used, i.e. when a
 * <code>Connection</code> is released it is then available to be used again.
 * <p>
 *
 **/
public final class ConnectionPool {

	// Print debug information to System.err.
	private static final boolean debug = true;

	// Storage for the unused connections.
	private Vector free;

	// Storage for the allocated connections.
	private Vector used;

	// Connection information.

	private String url;
	private String user;
	private String password;
	private Properties info;

	// Initial Connections
	private int initialCons = 0;

	// Maximum number of concurrent connections allowed.
	private int maxCons = 0;

	// The number of connection that have been created.
	private int numCons = 0;

	// Whether to block until a connection is free when maxCons are in use.
	private boolean block;

	// Timeout waiting for a connection to be released when blocking.
	private long timeout;

	// Whether we should re-use connections or not
	private boolean reuseCons = true;

	/**
	 * Creates a new <code>ConnectionPool</code> with defaults for
	 * <code>Connection</code> characteristics. Specifies the url, user and password
	 * for <code>Connections</code> and the characteristics of the pool.
	 *
	 * @param url         The url for the database, as in
	 *                    jdbc:<em>subprotocol</em>:<em>subname</em>.
	 * @param user        The user to connect to the database as.
	 * @param password    The user's password.
	 * @param initialCons The number of connections to create now.
	 * @param maxCons     The maximum number of connections allowed. A value <= 0
	 *                    means "no limit". If maxCons > 0 and maxCons < initialCons
	 *                    then maxCons takes precedence.
	 * @param block       If a request for a connection should block until a
	 *                    connection is released when none are available and maxCons
	 *                    has been reached.
	 * @param timeout     Maximum time to wait for a connection to be released when
	 *                    maxCons are in use.
	 * @exception SQLException if a connection could not be established, this is the
	 *                         exception thrown by DriverManager.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.DriverManager#getConnection(String, String, String)
	 **/
	public ConnectionPool(String url, String user, String password, int initialCons, int maxCons, boolean block,
			long timeout) throws SQLException {

		this.url = url;
		this.user = user;
		this.password = password;
		this.initialCons = initialCons;
		this.maxCons = maxCons;
		this.block = block;
		this.timeout = timeout;

		// maxCons has precedence over initialCons

		// max 媛� initial 蹂대떎 �옉�쓣�븣�뒗 媛숆쾶 �븳�떎.
		if (maxCons > 0 && maxCons < initialCons)
			initialCons = maxCons;

		// Create vectors large enough to store all the connections we make now.
		free = new Vector(initialCons);
		used = new Vector(initialCons);

		// Create some connections.
		while (numCons < initialCons) {
			addConnection(); // free�씪�뒗 collection�뿉 留뚮뱾�뼱吏� con媛앹껜瑜� �샇異쒗슏�닔留뚰겮 ���옣�븳�떎 - 458 line
		}
	}

	/**
	 * Closes all unallocated <code>connections</code>, allocated
	 * <code>connections</code> are marked for closing when they are released.
	 *
	 * @see #releaseConnection
	 * @see java.sql.Connection#close
	 **/
	public synchronized void closeAll() {

		// Close unallocated connections
		Enumeration cons = ((Vector) free.clone()).elements();
		while (cons.hasMoreElements()) {
			Connection con = (Connection) cons.nextElement();

			free.removeElement(con);
			numCons--;

			try {
				con.close();
			} catch (SQLException e) {
				// The Connection appears to be broken anyway, so we will ignore it
			}
		}

		// Move allocated connections to a list of connections that are closed
		// when they are released.
		cons = ((Vector) used.clone()).elements();
		while (cons.hasMoreElements()) {
			Connection con = (Connection) cons.nextElement();

			used.removeElement(con);

		}
	}

	/**
	 * Gets the <code>block</code> property for the pool. The block values specifies
	 * whether a <code>getConnection()</code> request should wait for a
	 * <code>connection</code> to be release if the maximum allowed are all in use.
	 *
	 * @return The block property.
	 * @see #setBlock
	 * @see #getConnection
	 **/
	public boolean getBlock() {
		return block;
	}

	/**
	 * Gets a <code>Connection</code> from the pool.
	 *
	 * @return A connection
	 * @exception ConnectionPoolException if the maximum number of allowed
	 *                                    connections are all in use, and, the
	 *                                    "pool" is not blocking or the timeout
	 *                                    expired when waiting.
	 * @exception SQLException            if all existing connections are in use and
	 *                                    a new one could not be created, this is
	 *                                    the exception thrown by DriverManger when
	 *                                    attempting to get a new connection.
	 * @see java.sql.DriverManager
	 **/
	public Connection getConnection() throws SQLException {

		return getConnection(this.block, timeout);
	}

	/**
	 * Gets a <code>Connection</code> from the pool.
	 *
	 * @param block   If a request for a connection should block until a connection
	 *                is released when none are available and maxCons has been
	 *                reached, overrides the value specified at construction.
	 * @param timeout Maximum time to wait for a connection to be released when
	 *                maxCons are in use, overrides the values specified at
	 *                construction.
	 * @return A connection
	 * @exception ConnectionPoolException if the maximum number of allowed
	 *                                    connections are all in use, and, the
	 *                                    "pool" is not blocking or the timeout
	 *                                    expired when waiting.
	 * @exception SQLException            if all existing connections are in use and
	 *                                    a new one could not be created, this is
	 *                                    the exception thrown by DriverManger when
	 *                                    attempting to get a new connection.
	 * @see java.sql.DriverManager
	 **/
	public synchronized Connection getConnection(boolean block, long timeout) throws SQLException {

		if (free.isEmpty()) { // free �뿉 Connection 媛앹껜媛� �븯�굹�룄 �뾾�떎.!!!

			// None left, do we create more?
			if (maxCons <= 0 || numCons < maxCons) {
				addConnection(); // maxCons �닔留뚰겮 Connection 媛앹껜 �깮�꽦.
			} else if (block) // waiting ...... �옉�뾽�떆.
			{
				try {
					long start = System.currentTimeMillis();
					do {

						wait(timeout); // �떆媛꾨쭔�겮 wait() �닔�뻾.

						if (timeout > 0) {
							timeout -= System.currentTimeMillis() - start;
							if (timeout == 0) {
								timeout -= 1;
							}
						}
					} while (timeout >= 0 && free.isEmpty() && maxCons > 0 && numCons >= maxCons);
				} catch (InterruptedException ee) {
				}
				// Did anyone release a connection while we were waiting?
				if (free.isEmpty()) {
					/*
					 * OK, nothing on the free list, but someone may have released a connection that
					 * they closed, so the free list is empty but numCons is now < maxCons and we
					 * can create a new connection.
					 */
					if (maxCons <= 0 || numCons < maxCons) {
						addConnection();
					} else {
						throw new SQLException("Timeout waiting for a connection to be released");
					}
				}
			} else {
				// No connections left and we don't wait for more.
				throw new SQLException("Maximum number of allowed connections reached");
			}
		}
		// If we get this far at least one connection is available.\

		// �떎�젣�쟻�쑝濡� �닔�뻾�븯怨좎옄�븯�뒗 �옉�뾽.............
		Connection con;

		synchronized (used) // Vector used;
		{

			con = (Connection) free.lastElement(); // free �쓽 留덉�留� �슂�냼瑜� 鍮쇱��꽌 used �뿉 �궫�엯.
			// Move this connection off the free list
			free.removeElement(con);
			used.addElement(con);
		}
		return con;
	}// end getConnection()

	/**
	 * Gets the <code>maxCons</code> property for the pool. The maxCons values
	 * specifies the maximum number of <code>Connections</code> that can be
	 * allocated from the pool at any one time.
	 *
	 * @return The maxCons property.
	 * @see #getConnection
	 **/
	public int getMaxCons() {
		return maxCons;
	}

	/**
	 * Gets the reuseConnections property for the pool.
	 *
	 * @see #setReuseConnections
	 **/
	public boolean getReuseConnections() {
		return reuseCons;
	}

	/**
	 * Gets the <code>timeout</code> property for the pool. The timeout values
	 * specifies how long to wait for a <code>Connection</code> to be release if the
	 * maximum allowed are all in use when <code>getConnection()</code> is called
	 * and <code>block</code> is true.
	 *
	 * @return The timeout property.
	 * @see #setTimeout
	 **/
	public long getTimeout() {
		return timeout;
	}

	/**
	 * Gets the <code>url</code> property for the pool. This property is the url for
	 * <code>Connections</code> opened by this pool.
	 *
	 * @return The url property.
	 **/
	public String getUrl() {
		return url;
	}

	/**
	 * Release a connection back to the pool. Apps should take care not to use a
	 * <code>Connection</code> after it has been released as it may well be in use
	 * somewhere else, in which case the effects are undefined.
	 * <p>
	 * If an app has cause to close a <code>Connection</code> then it should still
	 * be released so that the count of active transactions is correct and a new
	 * <code>Connection</code> can be created to take its place.
	 * <p>
	 * A rollback is performed on the <code>Connection</code> so if autoCommit is
	 * false any changes made that have not been committed will be lost.
	 *
	 * @param con The Connection to put back in the pool.
	 * @exception UnownedConnectionException if the Connection did not come from
	 *                                       this pool.
	 * @see #getConnection
	 **/
	public synchronized void releaseConnection(Connection con) // used �뿉�꽌�뒗 �젣嫄�, free �뿉�꽌�뒗 �궫�엯.
			throws SQLException {

		boolean reuseThisCon = reuseCons; // reuseCons = true;

		if (used.contains(con)) {
			// Move this connection from the used list to the free list
			used.removeElement(con);
			numCons--;
		} else {
			throw new SQLException("Connection " + con + " did not come from this ConnectionPool");
		}

		try {

			if (reuseThisCon) {
				free.addElement(con);
				numCons++;
			} else {
				con.close();
			}

			// Wake up a thread waiting for a connection

			// wait �떆耳쒕넃�븘�뜕 寃껋쓣 源⑥슫�떎.
			notify();
		} catch (SQLException e) {
			/*
			 * The Connection seems to be broken, but it's off the used list and the
			 * connection count has been decremented.
			 */
			// Just to be sure
			try {
				con.close();
			} catch (Exception e2) {
				// we're expecting an SQLException here
			}
			notify();
		}
	}

	/**
	 * Sets the <code>block</code> property for the pool. The block values specifies
	 * whether a <code>getConnection()</code> request should wait for a
	 * <code>Connection</code> to be release if the maximum allowed are all in use.
	 * <p>
	 * Setting <code>block</code> to false will have no effect on any connection
	 * requests that have already begin to wait for a connection.
	 *
	 * @param block The block property.
	 * @see #getBlock
	 **/
	public void setBlock(boolean block) {
		this.block = block;
	}

	/**
	 * Sets the reuseConnections property on the pool. If this property is false
	 * then whenever a <code>Connection</code> is released it will be closed.
	 *
	 * @see #getReuseConnections
	 * @see #releaseConnection
	 * @see java.sql.Connection
	 * @see java.sql.Connection#close
	 **/
	public synchronized void setReuseConnections(boolean reuseCons) {
		this.reuseCons = reuseCons;
	}

	/**
	 * Sets the <code>timeout</code> property for the pool. The timeout values
	 * specifies how long to wait for a <code>Connection</code> to be release if the
	 * maximum allowed are all in use when <code>getConnection()</code> is called
	 * and <code>block</code> is true.
	 * <p>
	 * Setting <code>timeout</code> will have no effect on any
	 * <code>Connection</code> requests that have already begin to wait for a
	 * <code>Connection</code>.
	 *
	 * @return The timeout property.
	 * @see #getTimeout
	 **/
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	/**
	 * Adds a new <code>Connection</code> to the pool.
	 *
	 * @exception SQLException if a connection could not be established.
	 **/
	private void addConnection() throws SQLException {
		free.addElement(getNewConnection());
	}

	/**
	 * Gets a new <code>Connection</code>.
	 *
	 * @exception SQLException if a connection could not be established.
	 **/
	private Connection getNewConnection() throws SQLException {

		Connection con = null;

		System.out.println("About to connect to " + url);
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		++numCons;
		return con;
	}

}
