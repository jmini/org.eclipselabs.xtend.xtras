package org.eclipselabs.xtend.xtras.javagen.util

import java.io.File
import org.eclipselabs.xtend.xtras.javagen.holder.ClassHolder
import org.eclipselabs.xtend.xtras.javagen.holder.FileHolder
import org.eclipselabs.xtend.xtras.javagen.holder.ImportsHolder

/**
 * @author jbr
 *
 */
class SourceCodeUtility {
	def static FileHolder createSourceFile(ClassHolder declaringClass, ImportsHolder imports, String content, File srcFolder) {
		val parts = declaringClass.packageName.split("\\.")
		val folder = parts.fold(srcFolder, [File parent, String childName | new File(parent, childName)])
		val file = new File(folder, declaringClass.className + ".java")
		
		new FileHolder => [
			it.file = file
			it.content = "package " + declaringClass.packageName + ";\n" + imports.toText + "\n" + content
		]
	}
}