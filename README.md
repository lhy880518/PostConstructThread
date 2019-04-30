# PostConstructThread

## 구조
* [@PostConstruct](https://gist.github.com/lhy880518/f3875cd2f2b1198e80ebc14acbeb80a5)를 이용한 Tread 초기화 및 실행 처리
* SafeTurnOff를 이용하여 properties파일 수정만으로 Thread를 제어하는 방법사용
~~~
public class SafeTurnOff implements Runnable{

    public static boolean readyToWork = true;

    public static final int THREAD_TERM_MILLISECONDS = 5000;
    public static final int THREAD_INSERT_TERM_MILLISECONDS = 1000;

    @Override
    public void run() {
        while (true){
            try {
                // properties 파일을 읽어서 실시간으로 true false값으로 변화시킵니다.
                ClassPathResource classPathResource = new ClassPathResource("static/properties/threadInfo.properties");
                File file = classPathResource.getFile();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    String line = scanner.nextLine();
                    if(line.contains("ready.to.work")){
                        if(line.contains("false")){
                            this.readyToWork = false;
                        }else{
                            this.readyToWork = true;
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
~~~
~~~
// SafeTurnOff.readyToWork를 이용하여 while문을 빠져오게 합니다.
while (SafeTurnOff.readyToWork){
    try{
        commonQueue.offer(String.valueOf(System.currentTimeMillis()));
        log.info("InsertQueueThread = {}",commonQueue);
        Thread.sleep(SafeTurnOff.THREAD_INSERT_TERM_MILLISECONDS);
    }catch (Exception e){
        e.printStackTrace();
    }
}
~~~

* postConstructThreadServiceImpl내부의 private LinkedBlockingQueue<String> commonQueue;를 공유하여 queue를 사용합니다.