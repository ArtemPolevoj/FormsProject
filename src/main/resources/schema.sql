-- индексация по полям будет быстрее
CREATE INDEX if not exists idx_акты_осмотров ON акты_осмотров USING GIN (акт_осмотра jsonb_path_ops);