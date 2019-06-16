export interface IOffice {
  id?: string;
  officeCity?: string;
  officeCountry?: string;
}

export class Office implements IOffice {
  constructor(public id?: string, public officeCity?: string, public officeCountry?: string) {}
}
