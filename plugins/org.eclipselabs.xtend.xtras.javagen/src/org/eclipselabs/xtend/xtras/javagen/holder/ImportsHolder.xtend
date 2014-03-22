package org.eclipselabs.xtend.xtras.javagen.holder

import java.util.HashMap
import com.google.common.base.CharMatcher

class ImportsHolder {
	val imports = new HashMap<String, ClassHolder>
	val excluded = new HashMap<String, ClassHolder>
	
	new(ClassHolder classHolder) {
		excluded.addImport(classHolder)
	}
	
	def useClass(String fullClassName) {
		val pos = CharMatcher::is('.').lastIndexIn(fullClassName)
		return if(pos > -1) {
			useClass(new ClassHolder(fullClassName.substring(0, pos), fullClassName.substring(pos + 1)))
		} else {
			useClass(new ClassHolder(null, fullClassName))
		}
	}
	
	def useClass(String packageName, String className) {
		return useClass(new ClassHolder(packageName, className))
	}
	
	def useClass(ClassHolder classHolder) {
		if(classHolder.packageName == null || classHolder.packageName == "java.lang") {
			return classHolder.className
		} else if(excluded.containsKey(classHolder.className)) {
			if(excluded.get(classHolder.className) == classHolder) {
				return classHolder.className
			} else {
				return classHolder.packageName + "." + classHolder.className
			}
		} else if(imports.containsKey(classHolder.className)) {
			if(imports.get(classHolder.className) == classHolder) {
				return classHolder.className
			} else {
				return classHolder.packageName + "." + classHolder.className
			}
		} else {
			imports.addImport(classHolder)
			return classHolder.className
		}
	}
	
	def useClassWithGenerics(ClassHolder classHolder, ClassHolder... genericsParams) {
		return useClass(classHolder) + genericsParams.join("<", ", ", ">", [ClassHolder p | useClass(p)])
	}
	
	def private addImport(HashMap<String, ClassHolder> map, ClassHolder ClassHolder) {
		map.put(ClassHolder.className, ClassHolder)
	}
	
	def toText() '''
	«FOR i : imports.values.sortBy[packageName+"."+className]»
	import «i.packageName».«i.className»;
	«ENDFOR»
	'''
}
