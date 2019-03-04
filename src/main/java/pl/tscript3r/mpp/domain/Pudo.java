package pl.tscript3r.mpp.domain;

public class Pudo {
    private final Integer from;
    private final Integer to;
    private final String code;

    public Pudo(Integer from, Integer to, String code) {
        this.from = from;
        this.to = to;
        this.code = code;
    }

    public Boolean match(Integer zipCode) {
        return from <= zipCode && to >= zipCode;
    }

    public Integer totalRange() {
        return to - from;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Pudo{" +
                "from=" + from +
                ", to=" + to +
                ", code='" + code + '\'' +
                '}';
    }
}
