package org.eclipselabs.xtend.xtras.ecore.extentions

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EEnumLiteral
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature

class ECoreOperatorExtensions {
	
	def static operator_tripleGreaterThan(EClass eClass, EClass parentEClass) {
		eClass.getESuperTypes.add(parentEClass)
	}

	def static operator_add(EPackage ePackage, EClassifier eClassifier) {
		ePackage.getEClassifiers.add(eClassifier);
	}
	
	def static operator_add(EClass eClass, EStructuralFeature eStructuralFeature) {
		eClass.getEStructuralFeatures.add(eStructuralFeature);
	}
	
	def static operator_add(EEnum en, EEnumLiteral eEnumLiteral) {
		en.getELiterals.add(eEnumLiteral);
	}
}