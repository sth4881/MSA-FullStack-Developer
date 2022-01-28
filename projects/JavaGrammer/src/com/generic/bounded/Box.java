package com.generic.bounded;

public class Box<T> {
    private T t;          

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends String> void inspect(U u){
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
//        integerBox.set(new Integer(10));
        integerBox.set(10); // T를 10으로 설정
        integerBox.inspect("some text"); // inspect 메소드의 Bounded Type을 String으로 변경함으로써 오류 해결
    }
}