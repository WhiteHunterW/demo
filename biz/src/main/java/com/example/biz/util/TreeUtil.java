package com.example.biz.util;

import com.alibaba.fastjson.JSON;
import com.example.biz.data.Menu;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * java8 函数的应用场景之一
 * @author wenzeng
 * @date 2024/12/31
 */
public class TreeUtil {


    /**
     * 构造树
     * @param list 数据列表
     * @param parentIdGetter 获取parentId groupingBy不支持 mappingfunction为空
     * @param idGetter 获取ID
     * @param checkRoot 校验是否根结点
     * @param setChildren 填充子节点
     * @return
     * @param <T>
     * @param <E>
     */
    public static <T, E> List<E> makeTree(List<E> list, Function<E, T> parentIdGetter, Function<E, T> idGetter,
                                          Predicate<E> checkRoot, BiConsumer<E, List<E>> setChildren) {
        List<E> result = new ArrayList<>();
        // 不支持parentId为空的场景
        //Map<T, List<E>> parentMap = list.stream().collect(Collectors.groupingBy(parentIdGetter, Collectors.toList()));
        Map<T, List<E>> parentMap = new HashMap<>(16);
        for (E node : list) {
            T parentId = parentIdGetter.apply(node);
            parentMap.computeIfAbsent(parentId, k -> new ArrayList<>());
        }

        for (E node : list) {
            setChildren.accept(node, parentMap.getOrDefault(idGetter.apply(node), new ArrayList<>()));
            if(checkRoot.test(node)) {
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 搜索树
     * 匹配到的节点按层级返回
     * @param list 搜索树
     * @param tPredicate 匹配逻辑
     * @param getChildren 子节点
     * @return
     * @param <E>
     */
    public static <E> List<E> searchTree(List<E> list, Predicate<E> tPredicate, Function<E, List<E>> getChildren) {
        List<E> result = new ArrayList<>(10);
        for (E item : list) {
            List<E> childList = getChildren.apply(item);
            List<E> filteredChildren = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(childList)) {
                filteredChildren = searchTree(childList, tPredicate, getChildren);
            }
            if(tPredicate.test(item) || CollectionUtils.isNotEmpty(filteredChildren)) {
                result.add(item);
                // 还原下级子节点
                if(CollectionUtils.isNotEmpty(filteredChildren)) {
                    getChildren.apply(item).clear();
                    getChildren.apply(item).addAll(filteredChildren);
                }
            }
        }
        return result;
    }

    /**
     * 过滤树: 场景，判断树节点是否需要展示（根据用户权限）
     * @param list 需要过滤的树 上层不满足就不返回节点
     * @param tPredicate 过滤条件
     * @param getChildren 子节点
     * @param <E>
     */
    public static <E> List<E> filterTree(List<E> list, Predicate<E> tPredicate, Function<E, List<E>> getChildren) {
        return list.stream().filter(t ->  {
            if(tPredicate.test(t)) {
                // 判断子节点是否满足
                List<E> children = getChildren.apply(t);
                if(CollectionUtils.isNotEmpty(children)) {
                    filterTree(children, tPredicate, getChildren);
                }
                return true;
            }
            return false;
        }).collect(Collectors.toList());
    }

    /**
     * 排序树
     * @param list 需要排序的树
     * @param comparator 排序规则比较器
     * @param getChildren 子节点
     * @return
     * @param <E>
     */
    public static <E> List<E> sortTree(List<E> list, Comparator<? super E> comparator, Function<E, List<E>> getChildren) {
        list.forEach(t -> {
            List<E> children = getChildren.apply(t);
            if(CollectionUtils.isNotEmpty(children)) {
                sortTree(children, comparator, getChildren);
            }
        });
        return list.stream().sorted(comparator).collect(Collectors.toList());
    }

    /**
     * 搜索到树节点后，对满足条件的节点做特殊处理
     * @param tree 目标树
     * @param predicate 搜索规则
     * @param getChildren 子节点
     * @param setChoose 特殊处理逻辑
     * @return
     * @param <E>
     */
    public static <E> List<E> filterAndHandler(List<E> tree, Predicate<E> predicate, Function<E, List<E>> getChildren,
                                               BiConsumer<E, Boolean> setChoose) {
        return tree.stream().filter(item -> {
            if (predicate.test(item)) {
                setChoose.accept(item, true);
            } else {
                setChoose.accept(item, false);
            }
            List<E> children = getChildren.apply(item);
            if(CollectionUtils.isNotEmpty(children)) {
                filterAndHandler(children, predicate, getChildren, setChoose);
            }
            return true;
        }).collect(Collectors.toList());
    }

    private static void flatMap(List<Menu> list, List<Menu> result) {
        list.forEach(t -> {
            result.add(t);
            if(CollectionUtils.isNotEmpty(t.getChildren())) {
                flatMap(t.getChildren(), result);
            }
        });
    }

    public static void main(String[] args) {
        String str = "{\n" +
                "    \"id\":1,\n" +
                "    \"parentId\":null,\n" +
                "    \"name\":\"中国\",\n" +
                "    \"hasPermission\":true,\n" +
                "    \"children\":[\n" +
                "        {\n" +
                "            \"id\":10,\n" +
                "            \"parentId\":1,\n" +
                "            \"name\":\"北京\",\n" +
                "            \"hasPermission\":true,\n" +
                "            \"children\":[\n" +
                "                {\n" +
                "                    \"id\":101,\n" +
                "                    \"parentId\":10,\n" +
                "                    \"name\":\"北京市\",\n" +
                "                    \"hasPermission\":false,\n" +
                "                    \"children\":null\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":11,\n" +
                "            \"parentId\":1,\n" +
                "            \"name\":\"四川省\",\n" +
                "            \"hasPermission\":false,\n" +
                "            \"children\":[\n" +
                "                {\n" +
                "                    \"id\":111,\n" +
                "                    \"parentId\":11,\n" +
                "                    \"name\":\"成都市\",\n" +
                "                    \"hasPermission\":true,\n" +
                "                    \"children\":null\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":12,\n" +
                "            \"parentId\":1,\n" +
                "            \"name\":\"湖南省\",\n" +
                "            \"hasPermission\":true,\n" +
                "            \"children\":[\n" +
                "                {\n" +
                "                    \"id\":121,\n" +
                "                    \"parentId\":12,\n" +
                "                    \"name\":\"长沙市\",\n" +
                "                    \"hasPermission\":true,\n" +
                "                    \"children\":null\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Menu menu = JSON.parseObject(str, Menu.class);
        /*// 平铺
        List<Menu> menuList = new ArrayList<>();
        flatMap(Lists.newArrayList(menu), menuList);
        // 1.转成树
        List<Menu> result = makeTree(menuList, Menu::getParentId, Menu::getId, t -> Objects.isNull(t.getParentId()), (node, children) -> {
            List<Menu> exist = node.getChildren();
            if(CollectionUtils.isNotEmpty(exist)) {
                exist.addAll(children);
            }
        });
        System.out.println(JSON.toJSONString(result));*/

        /*// 2.树的搜索
        List<Menu> result = searchTree(Lists.newArrayList(menu), t -> t.getName().contains("长沙"), Menu::getChildren);
        System.out.println(JSON.toJSONString(result));*/

        // 3.树的过滤
        List<Menu> result = filterTree(Lists.newArrayList(menu), Menu::getHasPermission, Menu::getChildren);
        System.out.println(JSON.toJSONString(result));
    }

}
