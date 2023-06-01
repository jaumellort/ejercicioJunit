package com.quantion.ejercicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	private @Getter Long id;
	private @Getter String name;
	private @Getter String surname;
	private @Getter String password;
	private @Getter String email;
	private @Getter int age;
	private @Getter boolean active;
	
		
}
