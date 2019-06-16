import { IRegion } from 'app/shared/model/region.model';

export interface ICountry {
  id?: string;
  countryName?: string;
  countryCode?: string;
  region?: IRegion;
}

export class Country implements ICountry {
  constructor(public id?: string, public countryName?: string, public countryCode?: string, public region?: IRegion) {}
}
