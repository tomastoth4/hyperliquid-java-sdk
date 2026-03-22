package io.github.hyperliquid.sdk.model.websocket;

public class WsTrade {
    private String coin;
    private String side;
    private String px;
    private String sz;
    private Long time;
    private String hash;
    private Long tid;

    public String getCoin() { return coin; }
    public void setCoin(String coin) { this.coin = coin; }

    public String getSide() { return side; }
    public void setSide(String side) { this.side = side; }

    public String getPx() { return px; }
    public void setPx(String px) { this.px = px; }

    public String getSz() { return sz; }
    public void setSz(String sz) { this.sz = sz; }

    public Long getTime() { return time; }
    public void setTime(Long time) { this.time = time; }

    public String getHash() { return hash; }
    public void setHash(String hash) { this.hash = hash; }

    public Long getTid() { return tid; }
    public void setTid(Long tid) { this.tid = tid; }
}
