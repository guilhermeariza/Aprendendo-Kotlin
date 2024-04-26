public class TrainingJava {

    public int addPrimitive(int i1, int i2){
        return i1 + i2;
    }
    public Integer add(Integer i1, Integer i2){
        return i1 + i2;
    }

    public Integer add2(Integer i1, Integer i2){
        return Integer.valueOf(i1.intValue() + i2.intValue());
    }

    //codigo da linha 7, ele é uma abreviação do q realmente acontece no java, que acontece na verdade oq ta na linha 11
    //tipo n faz sentido somar dois objetos, aí o q o java faz por debaixo dos panos quando vê um "+" é oq ta na linha 11


}
