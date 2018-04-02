INSERT INTO public.developers(developers_id, developers_user_uuid, developers_name, developers_description, developers_avatar, developers_email, developers_reg_date)
	VALUES (1, 'd1098e96-ca3d-11e7-abc4-cec278b6b50a', 'MountainPier', 'Designing fresh cool games since 2018', 'https://d30y9cdsu7xlg0.cloudfront.net/png/7572-200.png', 'developer@studio.ru', current_date);

INSERT INTO public.games(games_id, games_name, games_description, games_price, games_avatar, games_release_date)
	VALUES ('c3f27041-e605-456b-8676-4739824c3cf1', 'Chess', 'Turn-based action game with rich amount of different units', 0, 'https://pp.userapi.com/c626922/v626922567/2429b/IjwJ0ZMRHNo.jpg', current_date);

INSERT INTO public.games_developers(developers_developers_id, games_games_id)
	VALUES(1, 'c3f27041-e605-456b-8676-4739824c3cf1')
