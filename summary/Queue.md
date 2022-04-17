# Queue

> 자바의 Queue관련 메서드 차이점 

- 기능적 차이는 아니고 정상작동이 실패 시 결과가 다르다.
- 정상 작동 실패 시, 예외 발생/null 또는 false 반환 으로 구분된다.

|             | 예외 발생                  | 값 리턴         | 그외      |
|-------------|------------------------|--------------|---------|
| 추가(enqueue) | add(e)                 | offer(e)     ||
|             | IllegalStateException  | false        ||
| 삭제(dequeue) | remove()               | poll()       | clear() |
|             | NoSuchElementException | null 반환      | 큐 비우기   |
| 검사(peek)    | element()              | peek()       ||
|             | 꺼내고 삭제/null            | 꺼내고 무삭제/null ||

