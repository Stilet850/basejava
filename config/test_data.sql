INSERT into resume (uuid, full_name) VALUES
('d453f355-ae8f-446c-bbb0-54a44f61b971', 'NAME1'),
('e73a5f1a-98a5-4f8a-8c6d-70c899cfd598', 'NAME2'),
('38cf4b80-ef8d-4089-a18e-ff07adfe3e99', 'NAME3');

INSERT into contact(resume_uuid, type, value) values
('d453f355-ae8f-446c-bbb0-54a44f61b971', 'LANDLINE', '234324'),
('d453f355-ae8f-446c-bbb0-54a44f61b971', 'SKYPE', 'skype');