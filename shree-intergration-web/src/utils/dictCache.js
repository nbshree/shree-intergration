import httpAjax from "@/utils/httpAjax";


class DictCache {
    static dictMap = new Map();

    //该方法定义在Point.prototype上
    static getDict(dictType) {
        if (DictCache.dictMap.has(dictType)) {
            return DictCache.dictMap.get(dictType);
        } else {
            let dictItem = new DictItem(dictType);
            DictCache.dictMap.set(dictType, dictItem);
            return dictItem;
        }
    }
}

class DictItem {
    constructor(dictType) {
        this.dictType = dictType;
        this.valueMap = new Map();
        this.reload(this.dictType);
    }

    get DictType() {
        return this.dictType;
    }

    reload(dictType) {
        this.valueMap.clear();
        httpAjax.post('api/system/dict/queryListByType', {dictType: dictType})
            .then((result) => {
                if (result.status == "1") {
                    for (let vk of result.data) {
                        this.valueMap.set(vk.dictCode, vk);
                    }
                    this.dictType = dictType;
                }
            })
            .catch(() => {

            });
    }

    getItems() {
        return [...this.valueMap.values()];
    }

    getItem(code) {
        if (this.valueMap.has(code)) {
            let data = this.valueMap.get(code);
            if (data) {
                return data;
            }
        }
        return null;
    }

    getName(code) {
        if (this.valueMap.has(code)) {
            let data = this.valueMap.get(code);
            if (data) {
                return data.dictName;
            }
        }
        return null;
    }

    getValue(code) {
        if (this.valueMap.has(code)) {
            let data = this.valueMap.get(code);
            if (data) {
                return data.dictValue;
            }
        }
        return null;
    }


}

export default DictCache;
