package org.eclipselabs.xtend.xtras.ecore.extentions

import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EEnumLiteral
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference

import static extension org.eclipselabs.xtend.xtras.ecore.extentions.ECoreOperatorExtensions.*

class ECoreCreateExtensions {
	
	static val eCoreFactory = EcoreFactory::eINSTANCE
	
	def static EPackage createEPakage(String name, String nsPrefix, String nsUri) {
		var ePackage = eCoreFactory.createEPackage
		ePackage.name = name
		ePackage.nsPrefix = nsPrefix
		ePackage.nsURI = nsUri
		
		return ePackage
	}
	
	def static EDataType createDataType(EPackage ePackage, String name, String instanceTypeName) { 
		var eDataType = eCoreFactory.createEDataType
		eDataType.name = name
		eDataType.instanceTypeName = instanceTypeName
		ePackage += eDataType
		return eDataType
	}
	
	def static EEnum createEEnum(EPackage ePackage, String name) {
		val eEnum = eCoreFactory.createEEnum
		eEnum.name = name
		ePackage += eEnum
		
		return eEnum
	}
	
	def static EEnumLiteral createEEnumLiteral(EEnum eEnum, String name, int value) {
		val eEnumLiteral = eCoreFactory.createEEnumLiteral
		eEnumLiteral.name = name	
		eEnumLiteral.value = value
		eEnum += eEnumLiteral
		return eEnumLiteral
	}
	
	def static EClass createEClass(EPackage ePackage, String name) { 
		val eClass = eCoreFactory.createEClass
		eClass.name = name
		ePackage += eClass
		return eClass
	}

	def static EAttribute createEAttribute(EClass eClass, String name, EClassifier eClassifier) {
		var eAttribute = eCoreFactory.createEAttribute
		eAttribute.name = name
		eAttribute.EType = eClassifier
		
		eClass  += eAttribute
		return eAttribute
	}
	
	def static EReference createEReference(EClass eClass, String name, EClassifier eClassifier) {
		val eReference = eCoreFactory.createEReference
		eReference.name = name
		eReference.EType = eClassifier
		
		eClass += eReference
		return eReference
	}
}