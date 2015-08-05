package org.personal.mason;

/**
 * Created by mason on 8/6/15.
 */
public interface IServer {
    /**
     * 启动服务器
     */
    public void start();

    /**
     * 重启程序
     */
    public void restart();

    /**
     * 停止程序运行
     */
    public void stop();

}
