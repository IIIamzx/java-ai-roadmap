

CREATE EXTENSION vector;


CREATE TABLE items (
   id serial PRIMARY KEY,
   name text,           -- 名称（如 "猫" 或 "苹果"）
   category text,       -- 类别（如 "动物" 或 "水果"）
   embedding vector(3)  -- 三维向量
);


-- 插入动物数据
INSERT INTO items (name, category, embedding) VALUES
('猫', '动物', '[0.9, 0.1, 0.2]'),
('狗', '动物', '[0.8, 0.2, 0.3]'),
('兔子', '动物', '[0.7, 0.3, 0.4]');

-- 插入水果数据
INSERT INTO items (name, category, embedding) VALUES
('苹果', '水果', '[0.2, 0.8, 0.1]'),
('香蕉', '水果', '[0.3, 0.7, 0.2]'),
('橙子', '水果', '[0.4, 0.6, 0.3]');



SELECT * FROM items
WHERE category = '动物'
ORDER BY embedding <-> '[0.85, 0.15, 0.25]'
LIMIT 1;


SELECT * FROM items
WHERE category = '水果'
ORDER BY embedding <-> '[0.35, 0.65, 0.25]'
LIMIT 1;






-- spring-ai集成 embedding --

--- 连接数据库   docker exec -it pgvector psql -U postgres -d embedding

CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS vector_store(
    id        uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    content   text,
    metadata  json,
    embedding vector(384)
);
CREATE INDEX ON vector_store USING HNSW (embedding vector_cosine_ops);



INSERT INTO public.vector_store (id, content, metadata, embedding)
VALUES ('ebdf64e5-bd04-41ce-9bd2-34b4f857d7ba',
        '无论您是初学者还是经验丰富的开发者，xmin 都能提供高效、灵活的工具，让您专注于业务需求而不是技术实现', '{
    "key": "value"
  }'::jsonb, '[1,2,3]'::vector);




