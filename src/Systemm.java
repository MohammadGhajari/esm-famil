import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Systemm {
    private String esm = "0", heivan = "0", shahr = "0", famil = "0", ghaza = "0", keshvar = "0", pooshak = "0", mive = "0", mashin = "0", gol = "0", ashia = "0";

    public Systemm(String fields) {

        if (fields.contains("a")) {
            ashia = "";
        }
        if (fields.contains("b")) {
            esm = "";
        }
        if (fields.contains("c")) {
            famil = "";
        }
        if (fields.contains("d")) {
            ghaza = "";
        }
        if (fields.contains("e")) {
            gol = "";
        }
        if (fields.contains("f")) {
            heivan = "";
        }
        if (fields.contains("g")) {
            keshvar = "";
        }
        if (fields.contains("h")) {
            mashin = "";
        }
        if (fields.contains("i")) {
            mive = "";
        }
        if (fields.contains("j")) {
            pooshak = "";
        }
        if (fields.contains("k")) {
            shahr = "";
        }

    }

    public String getEsm() {
        return esm;
    }

    public String getHeivan() {
        return heivan;
    }

    public String getShahr() {
        return shahr;
    }

    public String getFamil() {
        return famil;
    }

    public String getGhaza() {
        return ghaza;
    }

    public String getKeshvar() {
        return keshvar;
    }

    public String getPooshak() {
        return pooshak;
    }

    public String getMive() {
        return mive;
    }

    public String getMashin() {
        return mashin;
    }

    public String getGol() {
        return gol;
    }

    public String getAshia() {
        return ashia;
    }

    public void setEsm(String esm) {
        this.esm = esm;
    }

    public void setHeivan(String heivan) {
        this.heivan = heivan;
    }

    public void setShahr(String shahr) {
        this.shahr = shahr;
    }

    public void setFamil(String famil) {
        this.famil = famil;
    }

    public void setGhaza(String ghaza) {
        this.ghaza = ghaza;
    }

    public void setKeshvar(String keshvar) {
        this.keshvar = keshvar;
    }

    public void setPooshak(String pooshak) {
        this.pooshak = pooshak;
    }

    public void setMive(String mive) {
        this.mive = mive;
    }

    public void setMashin(String mashin) {
        this.mashin = mashin;
    }

    public void setGol(String gol) {
        this.gol = gol;
    }

    public void setAshia(String ashia) {
        this.ashia = ashia;
    }
}
