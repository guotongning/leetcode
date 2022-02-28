select original_data.dt,
       original_data.uid,
       delete_domain.count  as delete_domain_filter_uv,
       disable_domain.count as disable_domain_filter_uv,
       black_list.count as black_list_filter_uv
from (
         select dt, uid
         from (
                  select dt,
                         get_json_object(JSON, '$.uid') as uid
                  from hdp_ubu_tech_wei_defaultdb.new_client_visit_event_raw
                  where dt = '20211007'
                    and get_json_object(json, '$.ext.clientenum') = 2
              ) temp
         group by dt, uid
     ) original_data
         left join (
    select dt, uid, count(distinct open_id) as count
    from (
        select dt,
        get_json_object(JSON, '$.uid') as uid,
        get_json_object(json, '$.thirdUid') as open_id
        from hdp_ubu_tech_wei_defaultdb.new_client_visit_event_raw
        where dt = '20211007'
        and get_json_object(JSON, '$.ext.clientenum') = 2
        and get_json_object(JSON, '$.ext.visitStatus') = 1007
        ) temp
    group by dt, uid
) delete_domain on original_data.uid = delete_domain.uid
         left join (
    select dt, uid, count(distinct open_id) as count
    from (
        select dt,
        get_json_object(JSON, '$.uid') as uid,
        get_json_object(json, '$.thirdUid') as open_id
        from hdp_ubu_tech_wei_defaultdb.new_client_visit_event_raw
        where dt = '20211007'
        and get_json_object(JSON, '$.ext.clientenum') = 2
        and get_json_object(JSON, '$.ext.visitStatus') = 1008
        ) temp
    group by dt, uid
) disable_domain on original_data.uid = disable_domain.uid
         left join (
    select dt, uid, count(distinct open_id) as count
    from (
        select dt,
        get_json_object(JSON, '$.uid') as uid,
        get_json_object(json, '$.thirdUid') as open_id
        from hdp_ubu_tech_wei_defaultdb.new_client_visit_event_raw
        where dt = '20211007'
        and get_json_object(JSON, '$.ext.clientenum') = 2
        and get_json_object(JSON, '$.ext.visitStatus') = 1009
        ) temp
    group by dt, uid
) black_list on original_data.uid = black_list.uid
