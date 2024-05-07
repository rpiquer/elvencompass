import { Marker } from "./marker"

export class Location {
    id!:number;
    name!: string|null;
    description!: string|null;
    wikiLink!: string|null;
    mapName!: string;
    markers!: Marker[];

    constructor(name:string|null, description:string|null, wikiLink:string|null){
        this.name = name;
        this.description = description;
        this.wikiLink = wikiLink;
    }
}
