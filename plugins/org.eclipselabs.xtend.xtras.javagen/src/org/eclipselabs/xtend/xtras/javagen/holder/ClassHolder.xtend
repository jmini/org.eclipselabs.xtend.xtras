package org.eclipselabs.xtend.xtras.javagen.holder

@Data class ClassHolder {
	String packageName
	String className
}

class ExtendsClassHolder extends ClassHolder {
	new(String packageName, String className) {
		super(packageName, className)
	}
}