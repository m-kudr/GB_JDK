package Sem2Tasks;

public class FullstackDeveloper extends Developer implements Front, Back {
    @Override
    public void backWork() {
        System.out.println("backWork");
    }

    @Override
    public void frontWork() {
        System.out.println("Run frontWork");
    }
}
