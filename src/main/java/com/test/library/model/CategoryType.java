package com.test.library.model;

public enum CategoryType {
    RESEARCH_HISTORY("Arastirma - Tarih"),
    SCIENCE("Bilim"),
    COMIC("Cizgi Roman"),
    CHILD_AND_YOUTH("Cocuk ve Genclik"),
    LESSON_TEST_BOOKS("Ders / Sinav Kitapları"),
    RELIGION("Din ve Tasavvuf"),
    LITERATURE("Edebiyat"),
    EDUCATION_REFERENCE("Egitim Basvuru"),
    PHILOSOPHY("Felsefe"),
    FOREIGN_LANGUAGES("Foreign Languages"),
    HOBBY("Hobi"),
    MYTH_LEGEND("Mitoloji Efsane"),
    HUMOR("Mizah"),
    PRESTIGE_BOOKS("Prestij Kitapları"),
    ART_DESIGN("Sanat - Tasarım"),
    AUDIO_BOOKS("Sesli Kitaplar"),
    OTHER("Diğer");

    private final String value;
    private CategoryType(String value){
        this.value = value;
    }

    public String getValue(){return value; }

}
