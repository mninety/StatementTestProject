package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.*;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class Retry implements IAnnotationTransformer {
	 
	 @Override
	 public void transform(ITestAnnotation testannotation, Class testClass, Constructor testConstructor, Method testMethod) {
	 IRetryAnalyzer retry = testannotation.getRetryAnalyzer();
	 
	 if (retry == null) {
	 testannotation.setRetryAnalyzer(RetryAnalyzer.class);
	 }
	 
	 }
	}