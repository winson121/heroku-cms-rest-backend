package com.springcms.backendrestapi;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Test {

	public static void main(String[] args) {
		System.out.println(BCrypt.hashpw("delta", BCrypt.gensalt(15)));
		System.out.println(BCrypt.checkpw("delta", "$2a$15$k/9hHVP72tVgEtD4o1MK.e7fexMu5WZZvTi3Wke3Qp0ehGXgsePzq"));
	}

}
