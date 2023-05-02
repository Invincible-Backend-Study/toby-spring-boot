package tobyspring.helloboot.repository;

public interface HelloRepository {
    Hello findeHello(String name);

    void increaseCount(String name);

    default int countOf9(String name){
        Hello hello = findeHello(name);
        return hello == null ? 0 : hello.getCount();
    }

}
