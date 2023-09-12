# producer-consumer
## 프로젝트 목적

> 1차 목표: 생산자-소비자 패턴 개념만 본 뒤 코드를 작성해 봄으로써 race-condition 과 동시성 제어에 대한 개념 이해
>
> 2차 목표: jmh를 이용한 성능 테스트 및 성능 개선


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

## 성능 테스트 및 성능 개선

:clipboard: [성능 개선](https://www.notion.so/producer-consumer-59d25260c64d4309ac0534bcc108212a)

<br>



