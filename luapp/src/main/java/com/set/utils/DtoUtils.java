package com.set.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger; 
 
public class DtoUtils { 
 
private static Logger LOGGER = Logger.getLogger("AccountController.class");
 
 private static Map<Class<?>, Info> INFOS = Collections 
   .synchronizedMap(new HashMap<Class<?>, DtoUtils.Info>()); 
 
 /**
  * Returns the dispose field. Field annotated with {@link Dispose}. 
  *  
  * @param clazz 
  * @return 
  */ 
 public static Field getDisposeField(Class<?> clazz) { 
  Info info = getInfo(clazz); 
  return info.getDisposeField(); 
 } 
 
 /**
  * Returns the dispose method. Method annotated with {@link Dispose}. 
  *  
  * @param clazz 
  * @return 
  */ 
 public static Method getDisposeMethod(Class<?> clazz) { 
  Info info = getInfo(clazz); 
  return info.getDisposeMethod(); 
 } 
 
 /**
  * Returns true, if the given field is a dispose field. 
  *  
  * @param clazz 
  * @param fieldName 
  * @return 
  */ 
 public static boolean isDisposeField(Class<?> clazz, String fieldName) { 
  Info info = getInfo(clazz); 
  return info.getDisposeField() != null ? info.getDisposeField() 
    .getName().equals(fieldName) : false; 
 } 
 
 /**
  * Returns true, if the given method is a dispose method. 
  *  
  * @param clazz 
  * @param fieldName 
  * @return 
  */ 
 public static boolean isDisposeMethod(Class<?> clazz, String methodName) { 
  Info info = getInfo(clazz); 
  return info.getDisposeField() != null ? info.getDisposeMethod() 
    .getName().equals(methodName) : false; 
 } 
 
 /**
  * Tries to invoke the dispose method. 
  *  
  * @param obj 
  * @return true, if the method could be invoked. False otherwise. 
  */ 
 public static boolean invokeDisposeMethod(Object obj) { 
  Info info = getInfo(obj.getClass()); 
  if (info != null && info.getDisposeMethod() != null) { 
   try { 
    info.getDisposeMethod().invoke(obj, new Object[0]); 
   } catch (IllegalAccessException e) { 
    return false; 
   } catch (IllegalArgumentException e) { 
    return false; 
   } catch (InvocationTargetException e) { 
    return false; 
   } 
  } 
  return true; 
 } 
 
 /**
  * Returns the info for the given class. 
  *  
  * @param clazz 
  * @return 
  */ 
 protected static Info getInfo(Class<?> clazz) { 
  Info info = INFOS.get(clazz); 
  if (info == null) { 
   info = createInfo(clazz); 
  } 
  return info; 
 } 
 
 /**
  * Creates a new info. 
  *  
  * @param clazz 
  * @return info 
  */ 
 private static Info createInfo(Class<?> clazz) { 
  Info info = new Info(); 
  applyFieldInfo(clazz, info); 
  applyMethodInfo(clazz, info); 
 
  INFOS.put(clazz, info); 
 
  return info; 
 } 
 
 /**
  * Applies all required field infos to the info object. 
  *  
  * @param clazz 
  * @return 
  */ 
 private static void applyFieldInfo(Class<?> clazz, Info info) { 
  try { 
   for (Field field : clazz.getDeclaredFields()) { 
    if (field.getAnnotation(Dispose.class) != null) { 
     info.disposeField = field; 
     break; 
    } 
   } 
 
   if (info.disposeField == null) { 
    Class<?> superClass = clazz.getSuperclass(); 
    if (superClass != null) { 
     applyFieldInfo(superClass, info); 
    } 
   } 
  } catch (SecurityException e) { 
	  LOGGER.error(e.getLocalizedMessage()); 
  } 
 } 
 
 /**
  * Applies all required field infos to the info object. 
  *  
  * @param clazz 
  * @return 
  */ 
 private static void applyMethodInfo(Class<?> clazz, Info info) { 
  try { 
   for (Method method : clazz.getDeclaredMethods()) { 
    if (method.getAnnotation(Dispose.class) != null) { 
     info.disposeMethod = method; 
     break; 
    } 
   } 
 
   if (info.disposeMethod == null) { 
    Class<?> superClass = clazz.getSuperclass(); 
    if (superClass != null) { 
     applyMethodInfo(superClass, info); 
    } 
   } 
  } catch (SecurityException e) { 
   LOGGER.error("{}", e); 
  } 
 } 
 
 protected Field findField(Class<?> clazz, String name) { 
  try { 
   Field field = clazz.getDeclaredField(name); 
   return field; 
  } catch (NoSuchFieldException e) { 
   Class<?> superClass = clazz.getSuperclass(); 
   if (superClass != null) { 
    return findField(superClass, name); 
   } 
  } catch (SecurityException e) { 
   LOGGER.error("{}", e); 
  } 
  return null; 
 } 
 
 private static class Info { 
 
  private Field disposeField; 
  private Method disposeMethod; 
 
  public Field getDisposeField() { 
   return disposeField; 
  } 
 
  public Method getDisposeMethod() { 
   return disposeMethod; 
  } 
 
 } 
 
}
