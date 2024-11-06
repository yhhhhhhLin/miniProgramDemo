package shop.linyh.miniProgramDemo.utils;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author linzz
 * 工具类，用来转换List类型
 */
public class ConversionUtil {

    /**
     * @param <T> 原先元素类型
     * @param <V> 转换后元素类型
     * @param <R> 元素集合类型
     */
    public static <T, V, R extends Collection<V>> R mapperCollection(Collection<T> src,
                                                                     Function<T, V> function,
                                                                     Supplier<R> supplier) {
        return src.stream().map(function).collect(Collectors.toCollection(supplier));
    }
}
