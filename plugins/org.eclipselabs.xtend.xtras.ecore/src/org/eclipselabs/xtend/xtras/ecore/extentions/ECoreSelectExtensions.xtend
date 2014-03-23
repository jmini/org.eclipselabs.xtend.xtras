package org.eclipselabs.xtend.xtras.ecore.extentions

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EPackage

import static extension org.eclipselabs.xtend.xtras.ecore.extentions.ECoreCastExtensions.*

class ECoreSelectExtensions {
	
	def static Iterable<EClass> getEClasses(EPackage ePackage) {
		return ePackage.getEClassifiers.filter[e | e instanceof EClass].map[e | e.asEClass]
	}
	
	def static Iterable<EDataType> getEDataTypes(EPackage ePackage) {
		return ePackage.getEClassifiers.filter[e | e instanceof EDataType].map[e | e.asEDataType]
	}
	
	def static Iterable<EEnum> getEEnums(EPackage ePackage) {
		return ePackage.getEClassifiers.filter[e | e instanceof EEnum].map[e | e.asEEnum]
	}
	
	def static Iterable<EClass> getEChildrenTypes(EClass eClass, EPackage ePackage) {
		getEClasses(ePackage).filter[c | eClass != c && eClass.isSuperTypeOf(c)]
	}
}