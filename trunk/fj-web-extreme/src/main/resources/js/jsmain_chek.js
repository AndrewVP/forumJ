function m_chek()
{
   var main_chek = document.getElementById('main_ch');
   var ch_kol = parseInt(document.getElementById('nrw').value);
   for (var xch=0; xch<ch_kol; xch++){
      document.getElementById('ch'+xch.toFixed(0)).checked=main_chek.checked;
   }
}
