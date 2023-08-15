Dasturni funksiyalaridan foydalanish va tekshirish.

1. Registratsiya qilish. Yoki DataLoaderdagi  tayyor admin yoki userlardan foydalanish
2. Login qilish. Login qilishda email va password kiritiladi.
3. Login successful bo'lsa JWT Token qaytaradi.
4. Productga request yuborganda har doim "Authorization" deb nomlanadigan header qo'shiladi va qiymatiga JWT Token beriladi.
5. Userlarga role va permissionlarni faqat Admin rolidagi odam bera oladi.