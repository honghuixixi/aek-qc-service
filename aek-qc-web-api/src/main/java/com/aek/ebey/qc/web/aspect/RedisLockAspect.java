package com.aek.ebey.qc.web.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.aek.ebey.qc.web.aspect.RedisLock.LockFailAction;

/*@Aspect
@Component*/
/*public class RedisLockAspect{

  private static final Logger log = LoggerFactory.getLogger(RedisLockAspect.class);
  //execution(* com.ns..*(*,..)) and @within(com.ns.annotation.RedisLock)
  
  @Autowired
  private RedisTemplate redisTemplate;
  
  @Pointcut(value ="@annotation(com.aek.ebey.repair.web.aspect.RedisLock)")
  private void lockPoint(){}
  @Around("lockPoint()")
  public Object arround(ProceedingJoinPoint pjp) throws Throwable{
    MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
    Method method = methodSignature.getMethod();
    RedisLock lockInfo = method.getAnnotation(RedisLock.class);
    boolean lock = false;
    Object obj = null;
    while(!lock){
      long timestamp = System.currentTimeMillis()+lockInfo.keepMills();
      lock = setNX(lockInfo.value(), timestamp);
      //得到锁，已过期并且成功设置后旧的时间戳依然是过期的，可以认为获取到了锁(成功设置防止锁竞争)
      long now = System.currentTimeMillis();
      if(lock || ((now > getLock(lockInfo.value())) && (now > getSet(lockInfo.value(), timestamp)))){
        //得到锁,执行方法，释放锁
        log.info("得到锁...");
        obj = pjp.proceed();
        //不加这一行，对于只能执行一次的定时任务，时间差上不能保证另一个一定正好放弃
        if(lockInfo.action().equals(LockFailAction.CONTINUE)){
          delete(lockInfo.value());
        }
      }else{
        if(lockInfo.action().equals(LockFailAction.CONTINUE)){
          log.info("稍后重新请求锁...");
          Thread.currentThread().sleep(lockInfo.sleepMills());
        }else{
          log.info("放弃锁...");
          break;
        }
      }
    }
    return obj;
  }
  public void delete(String value) {
	  redisTemplate.delete(value);
	
  }
  public boolean setNX(String key,Long value){
    return redisTemplate.opsForValue().setIfAbsent(key, value);
  }
  public long getLock(String key){
    return (long) redisTemplate.opsForValue().get(key);
  }
  public Long getSet(String key,Long value){
    return (Long) redisTemplate.opsForValue().getAndSet(key, value);
  }
  public void releaseLock(String key){
    delete(key);
  }
}
*/