package uz.pdp.model;
// <id> – Tartib raqami;
// <Code> – Valyutaning sonli kodi. Masalan: 840, 978, 643 va boshqalar;
// <Ccy> – Valyutaning ramzli kodi (alfa-3). Masalan: USD, EUR, RUB va boshqalar;
// <CcyNm_RU> – Valyutaning rus tilidagi nomi;
// <CcyNm_UZ> – Valyutaning o‘zbek (lotin) tilidagi nomi;
// <CcyNm_UZC> – Valyutaning o‘zbek (kirillitsa) tilidagi nomi;
// <CcyNm_EN> – Valyutaning ingliz tilidagi nomi;
// <Nominal> – Valyutaning birliklar soni;
// <Rate> – Valyuta kursi;
// <Diff> – Valyutlar kurslari farqi;
// <Date> – Kursning amal qilish sanasi.

//{"id":69,
// "Code":"840",
// "Ccy":"USD",
// "CcyNm_RU":"\u0414\u043e\u043b\u043b\u0430\u0440 \u0421\u0428\u0410",
// "CcyNm_UZ":"AQSH dollari",
// "CcyNm_UZC":"\u0410\u049a\u0428 \u0434\u043e\u043b\u043b\u0430\u0440\u0438",
// "CcyNm_EN":"US Dollar",
// "Nominal":"1",
// "Rate":"10795.73",
// "Diff":"13.49",
// "Date":"10.12.2021"}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Currency {
    private int id;
    private int Code;
    private String Ccy;
    private String CcyNm_RU;
    private String CcyNm_UZ;
    private String CcyNm_UZC;
    private String CcyNm_EN;
    private int Nominal;
    private double Rate;
    private double Diff;
    private String Date;



}
