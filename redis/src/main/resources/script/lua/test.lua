-- 测试一下lua脚本调用redis命令
local queue = "WT:TREE:QUEUE"

-- 当前使用的标志
local REMARK = redis.call("get", queue)
if not REMARK then
    REMARK = ""
end

-- 森林ID
local FOREST_ID = 1

local INDEX = "WT:TREE:%s:INDEX"
INDEX = string.format(INDEX, (FOREST_ID..REMARK))
local COMMAND = "WT:TREE:%s:%s"

local i = redis.call("get", string.format(INDEX))
if not i then
    i = 0
end

-- 开始遍历value
for k, v in pairs(ARGV) do
    redis.call("set", string.format(COMMAND, (FOREST_ID..REMARK), (i+1)), v)
    i = i + 1
end

redis.call("set", INDEX, i)