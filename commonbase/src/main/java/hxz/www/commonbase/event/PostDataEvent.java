package hxz.www.commonbase.event;

/**
 * @date:2019-08-26
 * @author:andy
 */
public class PostDataEvent<T> {

    private String MessageCode;

    private T bean;

    public PostDataEvent(String messageCode, T bean) {
        MessageCode = messageCode;
        this.bean = bean;
    }

    public String getMessageCode() {
        return MessageCode;
    }


    public T getBean() {
        return bean;
    }

}
