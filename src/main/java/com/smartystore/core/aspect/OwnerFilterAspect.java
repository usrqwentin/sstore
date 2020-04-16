package com.smartystore.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Configuration
@EnableAspectJAutoProxy
@Aspect
@Component
@ComponentScan

//TODO: implementation
public class OwnerFilterAspect {
  @Pointcut("execution(* org.hibernate.SessionFactory.getCurrentSession(..))")
  protected void hibernateSessionFetch() {
  }

  //execution(* org.hibernate.SessionFactory.getCurrentSession(..))
  @AfterReturning(pointcut = "execution(* org.hibernate.SessionFactory.getCurrentSession(..))", returning = "result")
  public void enableGlobalFilter(JoinPoint joinPoint, Object result) {
    Session session = (Session) result;

    Filter f = session.getEnabledFilter("ownerFilter");
    session.enableFilter("ownerFilter").setParameter("ownerId", 1L);
  }
}
