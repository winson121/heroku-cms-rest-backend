package com.springcms.backendrestapi;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Test {

	public static void main(String[] args) {
		System.out.println(BCrypt.hashpw("teacher", BCrypt.gensalt(15)));
		System.out.println(BCrypt.checkpw("teacher", "$2a$15$w2p/tfSAGhiR2Vv54grtSOpfsjKUas6tBz6mXHgWK.PUT0pSX44vG"));
		System.out.println(new HashSet(new ArrayList<String>()));
	}

}
