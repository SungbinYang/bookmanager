-- call next value for hibernate_sequence;
insert into USER (`id`, `name`, `email`, `created_at`, `updated_at`) values (1, 'robert', 'robert@google.com', now(), now());

-- call next value for hibernate_sequence;
insert into USER (`id`, `name`, `email`, `created_at`, `updated_at`) values (2, 'dennis', 'dennis@google.com', now(), now());

-- call next value for hibernate_sequence;
insert into USER (`id`, `name`, `email`, `created_at`, `updated_at`) values (3, 'sophia', 'sophia@naver.com', now(), now());

-- call next value for hibernate_sequence;
insert into USER (`id`, `name`, `email`, `created_at`, `updated_at`) values (4, 'james', 'james@naver.com', now(), now());

-- call next value for hibernate_sequence;
insert into USER (`id`, `name`, `email`, `created_at`, `updated_at`) values (5, 'robert', 'robert@daum.net', now(), now());

insert into publisher(`id`, `name`) values (1, '비니출판사');

insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values (1, 'JPA CASCADE', 1, false, 100 );

insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values (2, 'SPRING SECURITY', 1, false, 200 );

insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values (3, 'Spring Boot', 1, true, 100);

insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values (1, '내 인생책', '너무너무 좋음', 5.0, 1, 1);

insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values (2, '너무 진도 빠름', '너무너무 별로', 3.0, 2, 2);

insert into comment(`id`, `comment`, `review_id`) values (1, '저도 좋았어요', 1);

insert into comment(`id`, `comment`, `review_id`) values (2, '저는 별로요', 1);

insert into comment(`id`, `comment`, `review_id`) values (3, '저도 그냥 그랬어요', 2);