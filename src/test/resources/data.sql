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

insert into book(`id`, `name`, `publisher_id`, `deleted`) values (1, 'JPA CASCADE', 1, false );

insert into book(`id`, `name`, `publisher_id`, `deleted`) values (2, 'SPRING SECURITY', 1, false );

insert into book(`id`, `name`, `publisher_id`, `deleted`) values (3, 'Spring Boot', 1, true);