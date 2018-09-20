package com.soft.wechat.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Layout {

	/**
	 * 布局名称
	 * @return
	 */
	public String frameName();
	
	/**
	 * 布局名称
	 * @return
	 */
	public String suffix() default "ftl";

}
