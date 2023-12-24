package Sem2Tasks;

/*
Описать команду разработчиков. В команде разработчиков могут находиться бэкендеры,
которые в состоянии писать серверный код, фронтендеры, которые могут программиро-
вать экранные формы, и фуллстэк разработчики, совмещающие в себе обе компетенции.
Реализовать класс фулстэк разработчика, создать экземпляр и последовательно вызвать
все его методы.
 */


public class Main {
    public static void main(String[] args) {
        FullstackDeveloper fDev = new FullstackDeveloper();
        fDev.backWork();
        fDev.frontWork();

        /*
        1. Дописать третье задание таким образом, чтобы в идентификатор типа Developer
        записывался объект FrontDeveloper, и далее вызывался метод frontWork(), не изменяя
        существующие интерфейсы, только код основного класса.
         */
        Developer dev = new FrontDeveloper();
        if (dev instanceof FrontDeveloper){
            ((FrontDeveloper) dev).frontWork();
        }
    }
}
