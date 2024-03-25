package com.example.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/*
-//참고 사이트 
-//https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging
-//https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte3:fdl:%EC%84%A4%EC%A0%95_%ED%8C%8C%EC%9D%BC%EC%9D%84_%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94_%EB%B0%A9%EB%B2%95
-///https://congsong.tistory.com/25 [개념을 잡는데 사용]
-
-//24-02-27 AOP 적용 실습 
-// @Aspect => AOP 선언 어노테이션 
-// @component =>Spring이 자동으로 클래스의 인스턴스를 생성하고 종속성을 포함하여 각종 lifecycle을 관리해준다. 
-// 이를 통해 개발자는 오브젝트 생성 및 관리에 대한 걱정보다 비즈니스 로직 자체에 집중할 수 있다고 한다
-//@Slf4j : lombok, logger
-
-// 해당 Aspcet는 example.test 를 주소로 잡고, test 내 모든 파일에 대한 메소드 실행시 AOP 실행 중 
-// AOp는 component 어노테이션을 통해 bean 객체로 등록을 해놨기에 언제 어디서든 호출 가능 
-
-//하지만 XMl의 동작 구조에 대한 이해가 어렵다. 
-
-/* 스프링 프레임워크의 전체 동작 순서 
- *  1. Request
- *  2. DispatcherServlet (Web.xml) -request intersept
- *  3. HandlerMapping(sevlet-context.xml)
- *  4. Controller[이하 servic 이후 과정 생략]
- *  5. DispatcherServlet 
- *  6. ViewResolver 
- *  7. View 
- *  8. Respone
- */

@Aspect
@Component
@Slf4j
public class LoggingAspect {
  

	/*대상 Method 가 실행되기 전 Advice 실행 
-	 *  advice 란 :Aspect 의 기능을 정의한 것으로 메서드의 
-	 *  실행 전, 후, 예외 처리 발생 시 실행되는 코드를 의미
-	 *  
-	 *  JoinPoint : 어드바이스를 적용할 위치를 의미 
-	 *  여기서 joinPoint에 if문을 통해서 특정 메소드의 로그만을 확인 할 수 있도록 만들 수도 있을 것 같다. 
-	 *  
-	 *  Proxy : advice가 적용되었을 때 생성되는 객체 의미 
-	 */
	
	@Before("execution(* com.example.test.*.*(..))")
	public void logBefore(JoinPoint joinpoint) {
		log.info("Before: "+ joinpoint.getSignature().getName());
	}
	
	
	//After : 대상 Method 가 실행된 후에 Advice 를 실행
	@After("execution(* com.example.test.*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		log.info("After : " + joinPoint.getSignature().getName());
	}
	
	/* AfterReturning: 대상 method 가 정상적으로 실행되고 반환된 후에 advice 를 실행
-	 * pointcut : advice를 적용할 JoinPoint의 선별 과정, 그 기능을 정의한 모듈 의미?
-	 * Weaving : pointcut에 의해서 결정된 타겟의 조인 포인트에 advice를 적용하는 것의미
-	 */
	@AfterReturning(pointcut ="execution(* com.example.test.*.*(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("AfterReturning: " + joinPoint.getSignature().getName() + " result: " + result);
    }
	
	
	// @AfterThrowing: 대상 메서드에서 error 발생 시 Advice 실행 
	@AfterThrowing(pointcut ="execution(* com.example.test.*.*(..))", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
	    log.info("AfterThrowing: " + joinPoint.getSignature().getName() + " exception: " + e.getMessage());
	}

	
	//Around : 대상 메서드 실행 전후, 또는 예외 발생 시  Advice 를 실행 
	//"execution(* com.study.domain..*Controller.*(..)) || execution(* com.study.domain..*Service.*(..)) || execution(* com.study.domain..*Mapper.*(..))"
	// 위와 같이 여러가지 단일 경로를 한번에 지정 가능 
	
	@Around("execution(* com.example.test.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
		log.info("Around before: " + joinPoint.getSignature().getName());
		Object result = joinPoint.proceed();
		log.info("Around after: "+ joinPoint.getSignature().getName());
		return result;
	}
	//Around는 5가지 타입 중 Method의 호출 자체를 제어가 할 수 있기에 가장 강력한 기능 제공 
	
	//execution 사용법
	//execution(PostResponse select*(..))
	// return타입이 PostResoncse 타입이고, Method이름이 select으로 시작, param이 0개 이상인 모든 메소드가 호출 될 때 
	
	//execution(* com.exmaple.test.*())
	// 해당 패키니 내의 파라미터가 없는 모든 메서드가 호출 시 
	
	//execution(* com.exmaple.test.*(..))
	// 해당 패키지 내의 파라미터가 0개 이상인 모든 메소드 호출 시 
	
	//execution(* com.example.test..select(*))
	// example 의 하위 패키지에 존재하는 select으로 싲가하고, 파라미터가 하나 이상인 모든 Method 호출 시 
	
	//execution(* com.example.test..select(*, *))
	// example 의 하위 패키지에 존재하는 select으로 싲가하고, 파라미터가 두 개인 모든 Method 호출 시 

	//execution(* com.example.test..*Controller.*(..)
	// example.test의 모든 하위 패키지중 XXXController와 같은 패턴의 이름을 가진 클래스에서 파라미터가 0개 이상인 메소드 
	//execution(* com.example.test..*Service.*(..)
	// example.test의 모든 하위 패키지중 XXXService와 같은 패턴의 이름을 가진 클래스에서 파라미터가 0개 이상인 메소드 
	
	//추가 과제 
	// log제어 
}