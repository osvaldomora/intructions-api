package mx.santander.fiduciarioplus.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * @author David Gonzalez - (Arquetipo creado por Santander Tecnologia Mexico)
 * 
 * Clase para la implementacion de manejo de errores de cache
 *
 */
public class CustomCacheErrorHandler implements CacheErrorHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomCacheErrorHandler.class);
    

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        handleTimeOutException(exception);
        LOGGER.error("Unable to get from cache " + cache.getName() + " : " + exception.getMessage());
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        handleTimeOutException(exception);
        LOGGER.error("Unable to put into cache " + cache.getName() + " : " + exception.getMessage());
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        handleTimeOutException(exception);
        LOGGER.error("Unable to evict from cache " + cache.getName() + " : " + exception.getMessage());
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        handleTimeOutException(exception);
        LOGGER.error("Unable to clean cache " + cache.getName() + " : " + exception.getMessage());
    }

    /**
     * We handle redis connection timeout exception , if the exception is handled then it is treated as a cache miss and
     * gets the data from actual storage
     * 
     * @param exception Excepcion ocurrida en runtime
     */
    private void handleTimeOutException(RuntimeException exception) {
//        if (exception instanceof RedisCommandTimeoutException) {
//            return;
//        }
    }
}