# producer-consumer
## 프로젝트 목적

> 1차 목표: 생산자-소비자 패턴 개념만 본 뒤 코드를 작성해 봄으로써 동시성 제어에 대한 개념 이해
>
> 2차 목표: jmh를 이용한 성능 테스트 및 성능 개선

개인 프로젝트로 멀티 스레드 사용 시 일어날 수 있는 동시성 제어에 대한 이해를 위해 ‘생산자-소비자 패턴’을 구현했습니다. 코드 성능 측정을 위해 ‘JMH’를 사용했고 성능 테스트 결과에 따라 코드를 개선했습니다. scale-out 한 환경에서 자바 애플리케이션을 이용한 동시성 제어를 하는 것은 큰 의미가 없다고 생각했기에 단일 서버에서의 동시성 제어라는 것을 가정하고 프로젝트를 진행했습니다. 



<br>

## 프로젝트 명세
### Producer, Consumer, Queue 개발
- Queue

데이터를 넣고 뺄 수 있는 push, pop interface 제공

synchronize block & wait(), notifyAll() 함수를 이용하여 동시성 제어

- Producer

Queue에 데이터를 producing 하는 interface 제공

- Consumer

Queue에서 데이터를 consume하는 interface 제공

process 함수는 Queue에 data가 있으면 data를 출력하고 없으면 Queue에 data가 없다면 data가 들어올때까지 기다리기. data가 들어오면 다시 data를 출력


<br>

## 프로젝트 진행하면서

### 클래스 설계 및 최초 작성

 MyQueue, Producer, Consumer라는 클래스를 설계했습니다. MyQueue는 데이터를 넣고 뺄 수 있는 push와 pop 메서드가 정의돼 있고, Producer와 Consumer는 각각의 스레드로서 동작하며 공유자원인 MyQueue에 데이터를 삽입, 삭제하도록 했습니다. 
 처음에는 MyQueue의 push, pop을 호출하는데 메서드 자체에 synchronized 키워드를 걸고 boolean 값을 기준으로 Producer가 MyQueue에 접근하면 Consumer가 접근하지 못하고, Consumer가 MyQueue에  접근하면 Producer가 접근하지 못하도록 했습니다. 그랬더니 작성해둔 테스트 코드의 업무를 수행하는 데 평균 수행 시간이 약 18090,782 ms가 걸렸습니다. 

 ### 개선
 
 boolean 값을 사용한 이유는 MyQueue에 데이터가 push 되고 pop 되는 순서가 보장돼야 한다고 생각해서였는데, 이렇게 되면 MyQueue의 capacity가 1인 것처럼 동작하게 되고 MyQueue 입장에서는 push와 pop만 제대로 이루어지면 되는 것이라는 것을 알게 됐습니다. 따라서 MyQueue의 capacity 만큼의 데이터가 있을 때 push 메서드 호출 시 wait 메서드를 호출해 스레드를 대기 상태로 변경해주고 MyQueue의 데이터 개수가 0일 때 pop 메서드 호출 시 마찬가지로 wait 메서드를 호출해 줬습니다. 메서드 전체에 감싸줬던 synchonized 메서드를 synchornized block 을 만들어 필요한 부분에만 감싸주고 반복적으로 MyQueue 데이터 개수를 체크해 위에 언급한 상태가 끝나면 notify를 호출해 줌으로써 대기 상태의 쓰레드를 깨워줬습니다. 위의 코드 개선 작업을 통해 작성해둔 테스트 코드의 업무를 수행하는 평균 수행 시간을 2007.615 ms로 개선할 수 있었습니다.

 ### 결론
 
 지속적 개선 의지를 가지고 위의 과정을 수행하면서 성능 테스트를 통해 코드 개선을 함으로써 평균 18090,782 ms에서 평균 2007.615 ms로 코드 성능을 개선하고 동시성 제어에 대한 이해도를 증진할 수 있었습니다.



## 성능 테스트 및 성능 개선

:clipboard: [성능 개선](https://www.notion.so/producer-consumer-59d25260c64d4309ac0534bcc108212a)

<br>



