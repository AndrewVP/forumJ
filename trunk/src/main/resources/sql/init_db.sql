INSERT INTO `users` (`id`, `nick`, `pass`, `mail`, `name`, `fam`, `sex`, `bith`, `reg`, `pass2`, `smail`, `sname`, `city`, `scity`, `country`, `scountry`, `ssex`, `icq`, `sicq`, `sbith`, `lang`, `h_ip`, `view_def`, `pp_def`, `pt_def`, `avatar`, `s_avatar`, `ok_avatar`, `v_avatars`, `fd_timezone`, `footer`, `ban`, `activate_code`, `is_active`) VALUES
(0, 'Гость', '', '', NULL, NULL, NULL, NULL, '2009-05-29 09:14:59', '', 0, 0, '', 0, '', 0, 0, '', 0, 0, 0, 0, 3, 30, 40, '', 1, 1, 1, 16, '', 0, 0, 0)
;
INSERT INTO `fdviews` (`id`, `user`, `name`, `d_cr`) VALUES
(1, 0, 'Форум', '2006-06-22 02:00:40'),
(2, 0, 'Избранное', '2006-06-22 02:00:40'),
(3, 0, 'Все вместе, кроме корзины', '2006-06-22 02:01:57'),
(4, 0, 'Все вместе', '2006-06-22 02:01:57'),
(5, 0, 'Корзина', '2006-06-22 02:03:02')
;
INSERT INTO `fdfolders` (`id`, `flname`, `user`, `d_cr`) VALUES
(1, 'Форум', 0, '2006-06-22 01:57:56'),
(2, 'Избранное', 0, '2006-06-22 01:57:56'),
(3, 'Корзина', 0, '2006-06-22 01:59:16')
;
INSERT INTO `fd_setup` (`curret_body_table`, `current_body_head_table`) VALUES ('fd_post001', 'fd_head001')
;
