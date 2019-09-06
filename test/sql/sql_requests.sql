SELECT r.uuid, r.full_name, c.type, c.value FROM resume r JOIN contact c on r.uuid=c.resume_uuid;
SELECT * FROM resume r LEFT JOIN contact c on r.uuid=c.resume_uuid;
SELECT * FROM resume r FULL OUTER JOIN contact c on r.uuid=c.resume_uuid;