package model.Enum;

public enum Tipo {
    EFETIVO("Efetivo"),
    TERCEIRIZADO("Terceirizado");

    private final String desc;

    Tipo(String desc){
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

    public static Tipo TipoPorNome(String desc){
        for(Tipo tipo : Tipo.values()){
            if (tipo.getDesc().equalsIgnoreCase(desc)){
                return tipo;
            }
        }
        return null;
    }
}
