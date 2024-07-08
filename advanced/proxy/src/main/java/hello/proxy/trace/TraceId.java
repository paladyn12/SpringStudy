package hello.proxy.trace;

import java.util.UUID;

public class TraceId {
    //로그 추적기를 만들기 위한 기반 데이터

    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    public TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8); //매우 길게 만들어 지는데 앞의 8자리만 사용
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }


    public TraceId previousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
