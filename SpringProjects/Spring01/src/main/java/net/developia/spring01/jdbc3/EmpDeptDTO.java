package net.developia.spring01.jdbc3;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmpDeptDTO implements Serializable {
	private String last_name;
	private int salary;
	private String job_id;
	private String department_name;
}
