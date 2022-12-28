package com.divergentsl.aspects;

import com.divergentsl.entity.Donor;
import com.divergentsl.exception.CustomRequestDataValidationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Component
@Aspect
@Slf4j
public class RequestDataValidateAspect {

    @Before("execution(* com.divergentsl.controller.PublicController.donorRegisterRequest(..))")
    public void before(JoinPoint joinPoint) throws Exception
    {
        Object[] params=joinPoint.getArgs();
        Donor donor= (Donor) params[0];

        String phone=donor.getPhone();
        String email=donor.getEmail();

        //Phone Validation
        if(phone==null || phone.isEmpty())
        {
            throw new CustomRequestDataValidationException("Phone Field Is Empty");
        }
        else
        {
            String regex = "[6-9]{1}[0-9]{9}";
            if(phone.matches(regex)==false)
            {
                throw new CustomRequestDataValidationException("Phone Field Is Invalid");
            }
        }



        //Email Validation
        if(email==null || email.isEmpty())
        {
            throw new CustomRequestDataValidationException("Name Field Is Invalid");
        }
        else
        {
            String regex= "^(.+)@(.+)$";
            if(email.matches(regex)==false)
            {
                throw new CustomRequestDataValidationException("Email Field Is Invalid");
            }
        }

        /*
        name;
        gender;
        age;
        bloodgroup;
        nearestbloodbank;
        address;
        disease;
        access = JsonProperty.Access.WRITE_ONLY)
        password;
        role;*/

    }
}

/*

    @After("execution(* com.divergentsl.service.TransactionService.*(..))")
    public void after(JoinPoint joinPoint)
    {
        Object[] params=joinPoint.getArgs();
        Account account=(Account)params[0];
        logger.warn("AFTER ADVICE: Initial Balance: "+account.getBalance());
    }

    @AfterReturning(pointcut="execution(* com.divergentsl.service.TransactionService.*(..))",returning = "result" )
    public void afterReturning(JoinPoint joinPoint,Object result)
    {
        Object[] params=joinPoint.getArgs();
        Account account=(Account)params[0];
        logger.warn("AFTER RETURNING: Transaction Status: "+result);
    }

    @Around("execution(* com.divergentsl.service.TransactionService.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint)
    {
        logger.warn("AROUND ADVICE(BEFORE): "+proceedingJoinPoint.getSignature().getName()+" METHOD EXECUTION");
        String status="";
        try {
            status = (String)proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        logger.warn("AROUND ADVICE(AFTER): "+proceedingJoinPoint.getSignature().getName()+" METHOD EXECUTION");
        logger.warn("AROUND ADVICE: Transaction Status: "+status);
    }

    @AfterThrowing(pointcut = "execution(* com.divergentsl.service.TransactionService.*(..))",throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint,Exception exception)
    {
        logger.warn("AFTER THROWING: "+exception.getClass().getName()+" In Transaction: "+exception.getMessage());
    }
}

*/