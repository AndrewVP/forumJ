<?
function fd_timezone_hr($id_timezone){
   switch($id_timezone) {
         case 1:
         // (GMT - 12:00)
         return -14;
         break;
         case 2:
         // (GMT - 11:00)
         return -13;
         break;
         case 3:
         // (GMT - 10:00)
         return -12;
         break;
         case 4:
         // (GMT - 9:00)
         return -11;
         break;
         case 5:
         // (GMT - 8:00)
         return -10;
         break;
         case 6:
         // (GMT - 7:00)
         return -9;
         break;
         case 7:
         // (GMT - 6:00)
         return -8;
         break;
         case 8:
         // (GMT - 5:00)
         return -7;
         break;
         case 9:
         // (GMT - 4:00)
         return -6;
         break;
         case 10:
         // (GMT - 3:30)
         return -5;
         break;
         case 11:
         // (GMT - 3:00)
         return -5;
         break;
         case 12:
         // (GMT - 2:00)
         return -4;
         break;
         case 13:
         // (GMT - 1:00)
         return -3;
         break;
         case 14:
         // (GMT)
         return -2;
         break;
         case 15:
         // (GMT + 1:00)
         return -1;
         break;
         case 16:
         // (GMT + 2:00)
         return 0;
         break;
         case 17:
         // (GMT + 3:00)
         return 1;
         break;
         case 18:
         // (GMT + 3:30)
         return +1;
         break;
         case 19:
         // (GMT + 4:00)
         return 2;
         break;
         case 20:
         // (GMT + 4:30)
         return 2;
         break;
         case 21:
         // (GMT + 5:00)
         return 3;
         break;
         case 22:
         // (GMT + 5:30)
         return 3;
         break;
         case 23:
         // (GMT + 6:00)
         return 4;
         break;
         case 24:
         // (GMT + 7:00)
         return 5;
         break;
         case 25:
         // (GMT + 8:00)
         return 6;
         break;
         case 26:
         // (GMT + 9:00)
         return 7;
         break;
         case 27:
         // (GMT + 9:30)
         return 7;
         break;
         case 28:
         // (GMT + 10:00)
         return 8;
         break;
         case 29:
         // (GMT + 11:00)
         return 9;
         break;
         case 30:
         // (GMT + 12:00)
         return 10;
         break;
   }
	
}
function fd_timezone_mn($id_timezone){
   switch($id_timezone) {
         case 10:
         // (GMT - 3:30)
         return -30;
         break;
         case 18:
         // (GMT + 3:30)
         return 30;
         break;
         case 20:
         // (GMT + 4:30)
         return 30;
         break;
         case 22:
         // (GMT + 5:30)
         return 30;
         break;
         case 27:
         // (GMT + 9:30)
         return 30;
         break;
         default:	
         return 0;
   }
}
?>