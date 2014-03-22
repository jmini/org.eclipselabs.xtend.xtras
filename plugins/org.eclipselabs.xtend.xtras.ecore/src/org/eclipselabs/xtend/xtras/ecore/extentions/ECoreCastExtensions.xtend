package org.eclipselabs.xtend.xtras.ecore.extentions

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EObject

class ECoreCastExtensions {
	def static EClass asEClass(EObject o) {
		return o as EClass;	
	}
	
	def static EDataType asEDataType(EObject o) {
		return o as EDataType;	
	}
	def static EEnum asEEnum(EObject o) {
		return o as EEnum;	
	}
}